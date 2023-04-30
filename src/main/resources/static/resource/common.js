$('select[data-value]').each(function(index, el) {
	const $el = $(el);
	
	const defaultValue = $el.attr('data-value').trim();
	console.log(defaultValue);
	
	if(defaultValue.length > 0){
		$el.val(defaultValue);
	}
});