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
					alert('Welcome ')
					window.open("products.html", "_self");
				})
			event.preventDefault();
		}
	}
});