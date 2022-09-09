var app = new Vue({
	 el: '#changeTraining',
	 data: {
		 training: {},
		 error: '',
		 trainers: [],
		 selectedTrainer: {}
	 },
	 mounted() {
		 axios.get('rest/training/getSelected')
			 .then((response) => {
				 this.training = response.data
				 axios.get('rest/users/' + this.training.trainerID)
					 .then((response) => {
						 this.selectedTrainer = response.data;
					 })
			 })

		 axios.get('rest/users/getAllTrainers')
			 .then((response) => {
				 this.trainers = response.data;
			 })
	 },
	 methods: {
		 changeTraining: function(event) {
			this.training.trainerID = this.selectedTrainer.id;
			axios.put('rest/training/updateTraining', this.training)
			 .then((response) => {
				 alert('Uspesno ste izmenili trening!')
			 })
			 event.preventDefault()
		 },
		  uploadImage: function() {
			 var fileData = event.target.files[0];
			 this.training.pictureURL = fileData.name;
			 
		 }
	 }
 });