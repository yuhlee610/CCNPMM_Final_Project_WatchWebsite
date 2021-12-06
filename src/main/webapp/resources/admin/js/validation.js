$(function () {
	$(document).ready(function () {

	$("#brand").validate( {
		errorElement: "span",
		errorClass:"text-danger",
		//onfocusout: false,
		onkeyup: false,
		//onclick: false,
		rules: {
			// price: {
			// 	min: 0,
			// 	max: 100000,
			// 	digits:true
			// },
			// discount: {
			// 	min: 0,
			// 	max: 100,
			// 	digits: true
			// },
			brandName: {
				required: true,
			
				maxlength:50,
			remote: {
					url: 'admin/brand/checkname',
				type: "get",
			 		data: {
						name: function () {
			 				return $('#brandName').val();
			 			}
			 		}
                 }
					
			 }
			

			
			
			
		},
		messages: {
			
			brandName: {
				required: "Please enter brand name",
			
				maxlength: "Please enter length less than or equal to 50",
			remote:  jQuery.validator.format("Brand name is already")


			}
			
			
		}
	});
	$("#material").validate( {
		errorElement: "span",
		errorClass:"text-danger",
		//onfocusout: false,
		onkeyup: false,
		//onclick: false,
		rules: {
			
			materialName: {
				required: true,
			
				maxlength:50,
			remote: {
					url: 'admin/material/checkname',
				type: "get",
			 		data: {
						name: function () {
			 				return $('#materialName').val();
			 			}
			 		}
                 }
					
			 }

			
			
			
		},
		messages: {
			
			materialName: {
				required: "Please enter material name",
			
				maxlength: "Please enter length less than or equal to 50",
			
remote:  jQuery.validator.format("Material name is already")

			}
			
		}
	});	
	});
});