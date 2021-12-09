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
	$("#energy").validate( {
		errorElement: "span",
		errorClass:"text-danger",
		//onfocusout: false,
		onkeyup: false,
		//onclick: false,
		rules: {
			
			energyName: {
				required: true,
			
				maxlength:50,
			remote: {
					url: 'admin/energy/checkname',
				type: "get",
			 		data: {
						name: function () {
			 				return $('#energyName').val();
			 			}
			 		}
                 }
					
			 }

			
			
			
		},
		messages: {
			
			energyName: {
				required: "Please enter energy name",
			
				maxlength: "Please enter length less than or equal to 50",
			
remote:  jQuery.validator.format("Energy name is already")

			}
			
		}
	});
	$("#product").validate( {
		
		errorElement: "span",
		errorClass:"text-danger",
		//onfocusout: false,
		onkeyup: false,
		//onclick: false,
		rules: {
			
			id: {
				required: true,
			
				maxlength:50,
			remote: {
					url: 'admin/product/checkid',
				type: "get",
			 		data: {
						name: function () {
			 				return $('#id').val();
			 			}
			 		}
                 }
					
			 },
		name: {
				required: true,
			
				maxlength:50,
			
					
			 },
		amount: {
				required: true,
			
				min: 0,
				max: 1000000,
				digits:true
			
					
			 },
		price: {
				required: true,
			
				min: 0,
				max: 1000000000,
				
			
					
			 },
		

			
			
			
		},
		messages: {
			
			id: {
				required: "Please enter product id",
			
				maxlength: "Please enter length less than or equal to 50",
			
remote:  jQuery.validator.format("Product id is already")

			},
			name: {
				required: "Please enter product name",
			
				maxlength: "Please enter length less than or equal to 50"
			},
			amount: {
				required: "Please enter amount",
			
				max: "Please enter amount less 1000000"
			},
			price: {
				required: "Please enter price",
			
				max: "Please enter price less 1000000000"
			}
		}
	});	
	$("#editproduct").validate( {
		
		errorElement: "span",
		errorClass:"text-danger",
		//onfocusout: false,
		onkeyup: false,
		//onclick: false,
		rules: {
			
			
		name: {
				required: true,
			
				maxlength:50,
			
					
			 },
		amount: {
				required: true,
			
				min: 0,
				max: 1000000,
				digits:true
			
					
			 },
		price: {
				required: true,
			
				min: 0,
				max: 1000000000,
				
			
					
			 },
		

			
			
			
		},
		messages: {
			
			
			name: {
				required: "Please enter product name",
			
				maxlength: "Please enter length less than or equal to 50"
			},
			amount: {
				required: "Please enter amount",
			
				max: "Please enter amount less 1000000"
			},
			price: {
				required: "Please enter price",
			
				max: "Please enter price less 1000000000"
			}
		}
	});	
	});
});