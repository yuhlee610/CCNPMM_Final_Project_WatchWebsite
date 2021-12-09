$('#addCM').on('show.bs.modal', function(e) {
	debugger
    var Id = $(e.relatedTarget).data('id');
var productId = $(e.relatedTarget).data('productid');
    $(e.currentTarget).find('input[name="idComment"]').val(Id);
$(e.currentTarget).find('input[name="productId"]').val(productId);
});