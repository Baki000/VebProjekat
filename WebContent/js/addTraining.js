var app = new Vue({
	 el: '#newTraining',
	 data: {
		 training: {},
		 error: '',
		 trainers: [],
		 izabraniTrener: {}
	 },
	 mounted() {
		 
		 axios.get('rest/users/getAllTrainers')
			 .then((response) => {
				 this.trainers = response.data;
			 })
	 },
	 methods: {
		 addTraining: function(event) {
			this.training.trenerIntId = this.izabraniTrener.intId;
			axios.post('rest/trening/', this.trening)
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
			 this.trening.slika = fileData.name;
			 
		 }
	 }
 });