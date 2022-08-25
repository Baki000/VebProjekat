var app = new Vue({
	el: '#newSC',
	data: {
		newSC : {} ,
		newLocation: {},
		error: '',
		freeManagers: null,
		imaSlobodnih: 'true',
		chosenManager: {}, 
		prikaziFormu: false,
		userToRegister: {},
		pravimoNovogMenadzera: false
	},
	mounted() {
		 
		axios.get('rest/users/getFreeManagers')
			 .then((response) => {console.log(response.data)
								  this.freeManagers = response.data
								})
		this.newSC = {
			id: '', name: null, centerType: null, content: null,  status: "Closed", location: null,
			imagePath: null, averageGrade: null, opens: null, closes: null}
		this.newLocation = {id: '', latitudeWidth: null, longitudeLength: null, street: null, city: null, postalCode: null}
		this.userToRegister = {id: '', username: null, password: null, name: null, surname: null, sex: null, birthDate: null, role: 'manager'}
	},
	methods: {
		createSportsCenter: function(event) {
			this.error = ""
			if (!this.newSC.name || !this.newSC.centerType) {
				this.error = "Sva polja moraju biti uneta1!";
				event.preventDefault();
				return;
			}
			if(!this.newLocation.longitudeLength || !this.newLocation.latitudeWidth || !this.newLocation.street || !this.newLocation.city || !this.newLocation.postalCode){
				this.error = "Sva polja moraju biti uneta2!";
				event.preventDefault();
				return;
			}
			if(this.chosenManager === null){
				this.chosenManager = this.freeManagers[0]
			}

			this.newSC.averageGrade = 1
			this.newSC.location = this.newLocation
			axios.post('rest/sportsCenters/addCenter', this.newSC)
				.then((response) => {
					alert('Uspesno ste kreirali sportski objekat!')
					this.newSC = response.data;
					this.chosenManager.sportsCenter = this.newSC;
					axios.put('rest/users/update', this.chosenManager)
						.then((response) => {
							alert('Sportski objekat dodat menadzeru')
						}).catch(() => {
							alert('Korisnicko name vec postoji1');
							this.error = "This sport object already exists.1";
							event.preventDefault();
							return;
						})
				}).catch(() =>{
					alert('This sport object already exists2.')
					 this.error = "This sport object already exists2.";
					 event.preventDefault();
					 return;
				})

			event.preventDefault();
		},
		selectManager: function(manager) {
			this.chosenManager = manager;
		},
		showForm: function(){
		   this.prikaziFormu = true;
	   },
	   createUser: function() {
		this.error = ""
			if (!this.chosenManager.userName || !this.chosenManager.password || !this.chosenManager.name || !this.chosenManager.surname
			 			|| !this.chosenManager.sex || !this.chosenManager.birthDate) {
				this.error = "Sva polja moraju biti uneta!";
				event.preventDefault();
				return;
			}
			this.chosenManager.role = 'manager';
			axios.post('rest/users/registration', this.chosenManager)
				.then((response) => {
					alert('Uspesno ste registrovali novog menadzera!')
					this.chosenManager = response.data
				}).catch(() =>{
					alert('Korisnicko name vec postoji!')
					event.preventDefault();
					return;
				})
			event.preventDefault();
	   },
	   uploadImage: function() {
			 var fileData = event.target.files[0];
			 this.newSC.imagePath = fileData.name;
			 
		 }
	}
});