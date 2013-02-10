var shift = 0;
var stepDescriptions = ['Build task graph', 'Build system graph', 'Immerse task', 'Statistics'];

function navigateToStep(step) {
	$('#stepouter').animate({left: '-' + 1002 * step + 'px'});
	$('#faderight').animate({left: '+' + 1002 * (step + 1) + 'px'});
	$('#fadeleft').animate({left: '+' + 1002 * step - 150 + 'px'});
	shift = step;
	setBreadscrumb();
}

function setBreadscrumb() {
	$('#navigation').html('');
	for (var i = 0; i < shift; i++) {
		$('#navigation').append('<li><a href="#" onclick="navigateToStep(' + i + ')">Step ' + (i + 1) + '</a> <span class="divider">/</span></li>');
	}
	$('#navigation').append('<li class="active">Step ' + (shift + 1) + '</li>');
	$('#navigation').append('<li id="description">' + stepDescriptions[shift] + '</li>');
	handleButtonsVisibility();
}

function handleButtonsVisibility() {
	if (shift == 0) {
		$('#left').css('visibility', 'hidden');
	} else {
		$('#left').css('visibility', 'visible');
	}
	if (shift == stepDescriptions.length - 1) {
		$('#right').css('visibility', 'hidden');
	} else {
		$('#right').css('visibility', 'visible');
	}
}

$(document).ready(function() {
	setBreadscrumb();
	$('#right').click(function() {
		$('#stepouter').animate({left: '-=1002px'});
		$('#faderight').animate({left: '+=1002px'});
		$('#fadeleft').animate({left: '+=1002px'});
		shift++;
		setBreadscrumb();
	});
	$('#left').click(function() {
		$('#stepouter').animate({left: '+=1002px'});
		$('#faderight').animate({left: '-=1002px'});
		$('#fadeleft').animate({left: '-=1002px'});
		shift--;
		setBreadscrumb();
	});
});
