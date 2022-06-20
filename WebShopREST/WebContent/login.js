var app = new Vue({
	el: '#loginForm',
	data: {
		userToLogin: {},
		error: ''
	},
	mounted() {
		this.userToLogin = {
			id: '', userName: null, password: null
		}
	},
	methods: {
		loginUser : function(event) {
			this.error = ""
			if (!this.userToLogin.userName || !this.userToLogin.password) {
				this.error = "Sva polja moraju biti uneta!";
				event.preventDefault();
				return;
			}
			axios.post('rest/users/login', this.userToLogin)
				.then((response) => {
					alert('Uspesno ste se ulogovali')
					window.open("products.html");
				})
			event.preventDefault();
		}
	}
});