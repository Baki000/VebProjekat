var app = new Vue({
	 el: '#changeTrainer',
	 data: {
		 training: {},
		 error: '',
		 trainers: [],
		 chosenTrainer: {}
	 },
	 mounted() {
		 axios.get('rest/training/getSelected')
			 .then((response) => {
				 this.training = response.data
				 axios.get('rest/users/' + this.training.trainerID)
					 .then((response) => {
						 this.chosenTrainer = response.data;
					 })
			 })

		 axios.get('rest/users/getAllTrainers')
			 .then((response) => {
				 this.trainers = response.data;
			 })
	 },
	 methods: {
		 changeTraining: function(event) {
			this.training.trainerID = this.chosenTrainer.id;
			axios.put('rest/training/', this.training)
			 .then((response) => {
				 alert('Uspesno ste izmenili trening!')
			 })
			 event.preventDefault()
		 },
		  uploadImage: function() {
			 var fileData = event.target.files[0];
			 this.trening.pictureURL = fileData.name;
			 
		 }
	 }
 });