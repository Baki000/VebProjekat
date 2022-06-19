var app = new Vue({
	el: '#registration',
	data: {
		products: null,
		title: "Primer Vue.js tehnologije na spisku proizvoda",
		selectedProduct: {},
		error: ''
	},
	
	methods: {
		
		createOrEditProduct: function (event) {
			this.error = ""
				axios.post('rest/users/registration', this.selectedProduct)
					.then((response) => {
						alert('Novi proizvod uspešno kreiran')
						this.products.push(response.data)
					})

			

			event.preventDefault();
		}
	}
});
