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
						this.products = response.data
						if(products){
							alert("User successfuly registered")
						}else{
							alert("User already exists")
						}
						window.open("login.html", "_self");
					})

			

			event.preventDefault();
		}
	}
});
