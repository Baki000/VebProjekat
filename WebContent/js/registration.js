var app = new Vue({
	el: '#registration',
	data: {
		products: null,
		title: "Primer Vue.js tehnologije na spisku proizvoda",
		mode: "BROWSE",
		selectedProduct: {},
		error: ''
	},
	mounted() {
		
	},
	methods: {
		
		createOrEditProduct: function (event) {
			this.error = ""
				axios.post('rest/users/registration', this.selectedProduct)
					.then((response) => {
						alert('Novi proizvod uspešno kreiran')
						this.mode = 'BROWSE'
						this.products.push(response.data)
					})

			

			event.preventDefault();
		}
	}
});
