var shift = 0;

$(document).ready(function() {
	$('#right').click(function() {
		if (shift != $('.step').length - 1) {
			$('#stepouter').animate({left: '-=1002px'});
			$('#faderight').animate({left: '+=1002px'});
			$('#fadeleft').animate({left: '+=1002px'});
			shift++;	
		}
	});
	$('#left').click(function() {
		if (shift != 0) {
			$('#stepouter').animate({left: '+=1002px'});
			$('#faderight').animate({left: '-=1002px'});
			$('#fadeleft').animate({left: '-=1002px'});
			shift--;	
		}
	});
});
