var app = new Vue({
	el: '#registration',
	data: {
		products: null,
		title: "User Registration",
		selectedProduct: {},
		error: ''
	},
	
	methods: {
		
		createOrEditProduct: function (event) {
			this.error = ""
				axios.post('rest/users/registration', this.selectedProduct)
					.then((response) => {
						alert('Novi korisnik uspešno registrovan')
						this.products.push(response.data)
					})

			

			event.preventDefault();
		}
	}
});
