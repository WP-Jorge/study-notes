class HD {
	static PENDING = 'pending'
	static FULFILLED = 'fulfilled'
	static REJECTED = 'rejected'
	constructor(executer) {
		this.status = HD.PENDING
		this.value = null
		this.callbacks = []
		try {
			executer(this.resolve.bind(this), this.reject.bind(this))
		} catch (err) {
			this.reject(err)
		}
	}
	resolve(value) {
		if (this.status === HD.PENDING) {
			this.status = HD.FULFILLED
			this.value = value
			setTimeout(() => {
				this.callbacks.map(callback => {
					callback.onFulfilled(value)
				})
			})
		}
	}
	reject(reason) {
		if (this.status === HD.PENDING) {
			this.status = HD.REJECTED
			this.value = reason
			setTimeout(() => {
				this.callbacks.map(callback => {
					callback.onRejected(reason)
				})
			})
		}
	}
	then(onFulfilled, onRejected) {
		if (typeof onFulfilled !== 'function') {
			onFulfilled = () => this.value
		}
		if (typeof onRejected !== 'function') {
			onRejected = () => this.value
		}
		let promise = new HD((resolve, reject) => {
			if (this.status === HD.PENDING) {
				this.callbacks.push({
					onFulfilled: value => {
						this.parse(promise, onFulfilled(value), resolve, reject)
					},
					onRejected: reason => {
						this.parse(promise, onRejected(reason), resolve, reject)
					}
				})
			}
			if (this.status === HD.FULFILLED) {
				setTimeout(() => {
					this.parse(
						promise,
						onFulfilled(this.value),
						resolve,
						reject
					)
				})
			}
			if (this.status === HD.REJECTED) {
				setTimeout(() => {
					this.parse(promise, onRejected(this.value), resolve, reject)
				})
			}
		})
		return promise
	}
	parse(promise, result, resolve, reject) {
		if (promise === result) {
			throw new TypeError('Chaining cycle detected')
		}
		try {
			if (result instanceof HD) {
				result.then(resolve, reject)
			} else {
				resolve(result)
			}
		} catch (err) {
			reject(err)
		}
	}
	static resolve(value) {
		return new HD((resolve, reject) => {
			if (value instanceof HD) {
				value.then(resolve, reject)
			} else {
				resolve(value)
			}
		})
	}
	static reject(value) {
		return new HD((resolve, reject) => {
			reject(value)
		})
	}
	static all(promises) {
		const values = []
		return new HD((resolve, reject) => {
			promises.array.forEach(promise => {
				promise.then(
					value => {
						resolves.push(value)
						if (values.length === promises.length) {
							resolve(values)
						}
					},
					reason => {
						reject(reason)
					}
				)
			})
		})
	}
	static race(promises) {
		return new HD((resolve, reject) => {
			promises.map(promise => {
				promises.then(
					value => {
						resolve(value)
					},
					reason => {
						reject(reason)
					}
				)
			})
		})
	}
}
