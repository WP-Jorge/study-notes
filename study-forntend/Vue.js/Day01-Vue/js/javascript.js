var nv = new Vue({
	el:'#div-msg',
	data:{
		msg:'啊啊啊啊啊啊啊啊啊啊啊啊',
		mytitle:'我的title',
		interval:null
	},
	methods:{
		show(){
			alert('hello')
		}
	}
})

var nv2 = new Vue({
	el:'#paomadeng',
	data:{
		msg:'猥琐发育，别浪~~！'
	},
	methods:{
		lang(){
			if(this.interval!=null){return}
			this.interval = setInterval( () => {
				var star = this.msg.substring(0,1);
				var end = this.msg.substring(1);
				this.msg = end + star;
			},400)
		},
		wei(){
			clearInterval(this.interval);
			this.interval = null;
		}
	}
})