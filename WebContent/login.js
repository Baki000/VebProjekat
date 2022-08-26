var app = new Vue({
	el: '#loginForm',
	data: {
		userToLogin: {}
		
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
				alert("Sva polja moraju biti uneta!")
				event.preventDefault();
				return;
			}
			axios.post('rest/users/login', this.userToLogin)
				.then((response) => {
					alert('Welcome ' + this.userToLogin.userName)
					window.open("main.html", "_self");
				}).catch(() =>{
					alert('Invalid username or password!')
					event.preventDefault()
					return;
				})
			event.preventDefault();
			return;
		}
	}
});