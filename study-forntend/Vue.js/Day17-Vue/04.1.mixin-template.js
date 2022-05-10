const mixinx = {
	data() {
		return {
			msg: 'mixin混入'
		}
	},
	mounted() {
		this.mixin();
	},
	methods: {
		mixin() {
			console.log(this.msg);
		}
	}
}
