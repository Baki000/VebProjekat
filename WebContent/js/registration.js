var app = new Vue({
	el: '#registration',
	data: {
		title: "User Registration",
		user: {},
		error: ''
	},
	
	methods: {
		
		registerUser: function (event) {
			this.error = ""
			if (!this.user.userName || !this.user.password || !this.user.name || !this.user.surname
			 			|| !this.user.sex || !this.user.birthDate) {
				this.error = "All fields must be filled!";
				event.preventDefault();
				return;
			}
			axios.post('rest/users/registration', this.user)
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
			return;
		}
	}
});
