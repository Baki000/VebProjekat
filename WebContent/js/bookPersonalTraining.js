var app = new Vue({
	 el: '#trainingBooking',
	 data: {
		 training: {},
		 error: '',
		 trainers: [],
		 centers: [],
		 selectedTrainer: {},
		 selectedCenter: {},
		 date: null,
		 time: null
	 },
	 mounted() {

		 axios.get('rest/users/getAllTrainers')
			 .then((response) => {
				 this.trainers = response.data;
				 axios.get('rest/sportsCenters/getAll')
			 		.then((response) => {
				 		this.centers = response.data;
			 		})
				 
			 })
	 },
	 methods: {
		 bookTraining: function(event) {
			this.training.trainingDate = this.date + " " + this.time;
			this.training.trainerId = this.selectedTrainer.id;
			this.training.trainingStatus = 'not canceled';
			this.training.centerId = this.selectedCenter.id;
			axios.post('rest/bookedTraining/', this.training)
			 .then((response) => {
				 alert('training successfuly booked!')
			 })
			 event.preventDefault()
		 }
	 }
 });