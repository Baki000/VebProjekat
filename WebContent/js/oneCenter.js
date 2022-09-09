var app = new Vue({
	el: '#viewSO',
	data: {
		sc: {},
		location: {},
		error: '',
		trainings:[]
	},
	mounted() {
		axios.get('rest/sportsCenters/getSelected')
		.then((response) => {this.sc = response.data;
			this.location = this.sc.location
			axios.get('rest/training/getAllTrainings', { params: { scID: this.sc.id } }).
			then((response) => {
				this.trainings = response.data;
			})
			})
		},
	methods: {
		prijavaNaTrening: function(t){
			axios.put('rest/users/checkIn/' + this.sc.id)
			.then((response) => {
				axios.post('rest/trainingHistory/checkIn', { intId: t.id })
				.then((response) => {
					alert('Uspesno ste se cekirali za trening!')
				})
			}).catch(() => {
				alert('Istekla Vam je clanarina ili ste potrosili sve termine!')
				return;
			})
		},
		zakaziPersonalni: function(){
			window.location.href = 'http://localhost:8080/WebShopREST/bookPersonalTraining.html';
		} 
	}
});