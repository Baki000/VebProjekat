var app = new Vue({
	el: '#managersCenter',
	data: {
		loggedUser: {},
		trainers: null,
		customers: null,
		id:'',
		trainings: null
	},
	mounted() {
		axios.get('rest/users/currentUser').then((response) => {
			this.loggedUser = response.data;
			this.id = this.loggedUser.sportsCenter.id;

			axios.get('rest/training/getTrainers', { params: { scID: this.id} }).
			then((response) => {
			this.trainers = response.data;
			})
			axios.get('rest/users/getCustomersforSC', { params: { scID: this.id} }).
			then((response) => {
			this.customers = response.data;
			})
			
			axios.get('rest/training/getAllTrainings', { params: { scID: this.id} }).
			then((response) => {
			this.trainings = response.data;
			})
			
		})
			
	},
	methods: {
		changeTraining: function(tr) {
			axios.post('rest/training/setSelected', tr)
			 .then((response) => {
				 window.location.href = 'http://localhost:8080/WebShopREST/izmenaTreninga.html';
			 })
		 }
	}
});