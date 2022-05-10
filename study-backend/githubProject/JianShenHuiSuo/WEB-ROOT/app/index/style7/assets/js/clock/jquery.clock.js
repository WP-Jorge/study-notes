
(function(jQuery)
{
  jQuery.fn.clock = function(options)
  {
    var defaults = {
      offset: '+0',
      type: 'analog'
    };
    var _this = this;
    var opts = jQuery.extend(defaults, options);

    setInterval( function() {
      var seconds = jQuery.calcTime(opts.offset).getSeconds();
      if(opts.type=='analog')
      {
        var sdegree = seconds * 6;
        var srotate = "rotate(" + sdegree + "deg)";
        jQuery(_this).find(".sec").css({"-moz-transform" : srotate, "-webkit-transform" : srotate});
      }
      else
      {
        jQuery(_this).find(".sec").html(seconds);
      }
    }, 1000 );

    setInterval( function() {
      var hours = jQuery.calcTime(opts.offset).getHours();
      var mins = jQuery.calcTime(opts.offset).getMinutes();
      if(opts.type=='analog')
      {
        var hdegree = hours * 30 + (mins / 2);
        var hrotate = "rotate(" + hdegree + "deg)";
        jQuery(_this).find(".hour").css({"-moz-transform" : hrotate, "-webkit-transform" : hrotate});
      }
      else
      {
        jQuery(_this).find(".hour").html(hours);
      }
      var meridiem = hours<12?'AM':'PM';
      jQuery(_this).find('.meridiem').html(meridiem);
    }, 1000 );

    setInterval( function() {
      var mins = jQuery.calcTime(opts.offset).getMinutes();
      if(opts.type=='analog')
      {
        var mdegree = mins * 6;
        var mrotate = "rotate(" + mdegree + "deg)";        
        jQuery(_this).find(".min").css({"-moz-transform" : mrotate, "-webkit-transform" : mrotate});                
      }
      else
      {
        jQuery(_this).find(".min").html(mins);
      }
    }, 1000 );
  }
})(jQuery);


jQuery.calcTime = function(offset) {


  d = new Date();


  utc = d.getTime() + (d.getTimezoneOffset() * 60000);


  nd = new Date(utc + (3600000*offset));


  return nd;
};
