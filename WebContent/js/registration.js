var app = new Vue({
	el: '#registration',
	data: {
		title: "User Registration",
		user: {},
		error: ''
	},
	
	methods: {
		
		registerUser: function (event) {
			this.user.role = "customer"
			this.error = ""
			if (!this.user.userName || !this.user.password || !this.user.name || !this.user.surname
			 			|| !this.user.sex || !this.user.birthDate) {
				this.error = "All fields must be filled!";
				event.preventDefault();
				return;
			}
			axios.post('rest/users/registration', this.user)
				.then((response) => {
					
					if(response.data === ""){
						alert("User already exists")
						
					}else{
						alert("User successfuly registered")
					}
					window.open("login.html", "_self");
				})

			

			event.preventDefault();
			return;
		}
	}
});
