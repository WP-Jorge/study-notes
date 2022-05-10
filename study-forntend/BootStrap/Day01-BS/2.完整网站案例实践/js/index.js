$(function() {
	$(window).on("resize", function() {
		// 1.1获取窗口宽度
		let clientW = $(window).width()
		// console.log(clientW)

		// 1.2设置临界值
		let showBigImg = clientW

		// 1.3获取所有item
		let $allItems = $(".item")
		// console.log($allItems)

		// 1.4遍历
		$allItems.each(function(key, item) {
			// 1.4.1取出图片路径
			let src = showBigImg >= 800 ? $(item).data("lg-img") : $(item).data("sm-img")
			imgUrl = "url('" + src + "')"

			// 1.4.2设置背景
			$(item).css({
				backgroundImage: imgUrl
			})

			// 1.4.3设置img标签
			if (showBigImg < 800) {
				let $img = "<img src='" + src + "'>"
				$(item).empty().append($img)
			} else {
				$(item).empty()
			}
		})
	})
	$(window).triggerHandler("resize")

	// 2.工具提示
	$('[data-toggle="tooltip"]').tooltip()
	
	// 3.停止轮播
	// $("#lk_carousel").click(function() {
	// 	$("#lk_carousel").carousel('pause');
	// });
	
	// 4.动态处理宽度
	$(window).on("resize", function(){
		let $ul = $("#lk_product .nav")
		let $allLis = $("[role='presentation']", $ul)
		let parentW = $ul.parent().width()
		// console.log($allLis)
		
		// 4.1遍历
		let totalW = 0
		$allLis.each(function(key, item){
			totalW += $(item).width()
		})
		// console.log(totalW)
		
		
		// 4.2设置宽度
		if(totalW > parentW){
			$ul.css({
				width: totalW + "px"
			})
		}else{
			$ul.css({
				width:100 + "%"
			})
		}
	})
	
	// 5.导航
	let $allLis = $(".nav li")
	$($allLis[0]).on("click", function(){
		$("html, body").animate({scrollTop: $("#lk_about").offset().top}, 1000)
		
	})
	$($allLis[1]).on("click", function(){
		$("html, body").animate({scrollTop: $("#lk_product").offset().top}, 1000)
		
	})
	$($allLis[2]).on("click", function(){
		$("html, body").animate({scrollTop: $("#lk_hot").offset().top}, 1000)
		
	})
	$($allLis[3]).on("click", function(){
		$("html, body").animate({scrollTop: $("#lk_hot").offset().top}, 1000)
		
	})
	$($allLis[4]).on("click", function(){
		$("html, body").animate({scrollTop: $("#lk_hot").offset().top}, 1000)
		
	})
	$($allLis[5]).on("click", function(){
		$("html, body").animate({scrollTop: $("#lk_footer").offset().top}, 1000)
		
	})
});
// 880-896
