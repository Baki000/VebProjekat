var app = new Vue({
	 el: '#newTraining',
	 data: {
		 training: {},
		 error: '',
		 trainers: [],
		 selectedTrainer: {}
	 },
	 mounted() {
		 
		 axios.get('rest/users/getAllTrainers')
			 .then((response) => {
				 this.trainers = response.data;
			 })
	 },
	 methods: {
		 addTraining: function(event) {
			this.training.trainerID = this.selectedTrainer.id;
			axios.post('rest/training/addTraining', this.training)
			 .then((response) => {
				 alert('Uspesno ste dodali trening!')
			 }).catch(() =>{
					alert('Takav naziv vec postoji!')
					event.preventDefault();
					return;
				})
			 event.preventDefault()
		 },
		  uploadImage: function() {
			 var fileData = event.target.files[0];
			 this.training.pictureURL = fileData.name;
			 
		 }
	 }
 });