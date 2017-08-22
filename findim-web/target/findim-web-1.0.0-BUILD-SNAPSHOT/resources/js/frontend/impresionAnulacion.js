function closeDiv() {
	window.close();
}

function printDivById(printpage) {
	var printContents = document.getElementById(printpage).innerHTML;
	var originalContents = document.body.innerHTML;

	document.body.innerHTML = printContents;

	window.print();
	document.body.innerHTML = originalContents;
	closeDiv();
}

$(window).load(function() {
	setTimeout(function () {
		$('#imprimirAnulacion').click();
	}, 500);
});