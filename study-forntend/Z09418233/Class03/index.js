let vm = new Vue({
	el: "#app",
	data: {
		goods: [{
				id: 1,
				name: 'iPhone 11 Pro',
				price: 6188,
				count: 1,
				disabled: true,
				checked: false
			},
			{
				id: 2,
				name: 'iPad Pro',
				price: 8699,
				count: 1,
				disabled: true,
				checked: false
			},
			{
				id: 3,
				name: 'MacBook Pro',
				price: 18999,
				count: 1,
				disabled: true,
				checked: false
			}
		],
		item: null,
		index: -1,
		checked: false
	},
	methods: {
		clickItem(item, index) {
			this.item = item
			this.index = index
		},
		decrease() {
			if (this.item.count > 1) {
				this.item.count--
			} else {
				this.item.disabled = true
			}
			if (this.item.count === 1) {
				this.item.disabled = true
			}
		},
		add() {
			this.item.disabled = false
			this.item.count++
		},
		remove() {
			this.goods.splice(this.index, 1)
		},
		getTotalPrice() {
			this.totalPrice = 0
			for (let i = 0; i < this.goods.length; i++) {
				if (this.goods[i].checked) {
					this.totalPrice = this.totalPrice + this.goods[i].count * this.goods[i].price
				}
			}
		},
		toAbled() {
			if (this.item.checked === true) {
				this.item.checked = false
			} else {
				this.item.checked = true
			}
		},
		checkAll() {
			for (let i = 0; i < this.goods.length; i++) {
				this.goods[i].checked = this.checked
			}
		},
		checkEveryone() {
			let flag = true;
			for (let i = 0; i < this.goods.length; i++) {
				if (!this.goods[i].checked) {
					flag = false
				}
			}
			this.checked = flag
		}
	},
	computed: {
		totalPrice() {
			let price = 0
			for (let i = 0; i < this.goods.length; i++) {
				if (this.goods[i].checked) {
					price = price + this.goods[i].count * this.goods[i].price
				}
			}
			return price
		}
	},
	filters: {
		priceFilter(price) {
			return price.toString().replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,')
		}
	}
})
