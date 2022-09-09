var app = new Vue({
	el: '#mojProfil',
	data: {
		newUser: {},
		error: '',
		istorijeTreninga: null,
		personalniTreninzi: null,
		grupniTreninzi: null,
		searchObjekat: "",
		pocetniDatum: "",
		pocetnoVreme: "",
		krajnjiDatum: "",
		krajnjeVreme: "",
		pocetno: "",
		krajnje: ""
	},
	mounted() {
		axios.get('rest/users/currentUser')
			.then((response) => {
				this.newUser = response.data;
				axios.get('rest/trainingHistory/getTHforUser', { params: { userID: this.newUser.id } }).
					then((response) => {
						this.istorijeTreninga = response.data;
					})

				axios.get('rest/bookedTraining/getPersonalTrainings', { params: { userID: this.newUser.id } }).
					then((response) => {
						this.personalniTreninzi = response.data;
					})

				axios.get('rest/training/getGroupTrainings', { params: { userID: this.newUser.id } }).
					then((response) => {
						this.grupniTreninzi = response.data;
					})
			})
	},
	methods: {
		updateUser: function(event) {
			axios.put('rest/users/update', this.newUser)
				.then((response) => {
					alert('Podaci su uspesno promenjeni ')
				})
			event.preventDefault();
		},
		pretraziTreninge: function(event) {
			this.pocetno = this.pocetniDatum + " " + this.pocetnoVreme;
			this.krajnje = this.krajnjiDatum + " " + this.krajnjeVreme;
			axios.get('rest/trainingHistory/search', { params: { searchObjekat: this.searchObjekat, pocetno: this.pocetno, krajnje: this.krajnje } })
				.then(response => (this.istorijeTreninga = response.data))
		},
		cancelTraining: function(pt) {
			axios.delete('rest/bookedTraining/cancelTraining' + pt.id)
				.then((response) => {
					alert('Training successfuly canceled!');
					this.personalniTreninzi = this.personalniTreninzi.filter((p)=> p.id !== pt.id)

				}).catch(() =>{
					alert('Can not cancel! Training date in less than two days!')
					event.preventDefault();
					return;
				})
		}
	}
});