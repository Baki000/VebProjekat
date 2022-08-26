var app = new Vue({
	el: '#registerTrainer',
	data: {
		userToRegister: {},
		error: ''
	},
	mounted() {
		this.userToRegister = {
			id: '', userName: null, password: null, name: null, surname: null, sex: null, birthDate: null, role: 'trainer'
		}
	},
	methods: {
		registerTrainer: function(event) {
			this.error = ""
			if (!this.userToRegister.userName || !this.userToRegister.password || !this.userToRegister.name || !this.userToRegister.surname
			 			|| !this.userToRegister.sex || !this.userToRegister.birthDate) {
				this.error = "Sva polja moraju biti uneta!";
				event.preventDefault();
				return;
			}
			axios.post('rest/users/registration', this.userToRegister)
				.then((response) => {
					if(response.data === ""){
						alert('Korisnicko ime vec postoji!')
					}else{
						alert('Uspesno ste kreirali trenera!')
					}
				})
			event.preventDefault();
			return;
		}
	}
});