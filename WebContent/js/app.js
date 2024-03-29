var app = new Vue({
    el: '#sports_centers',
    data: {
        sports_centers: null,
        users: null,
        title: "Sports Centers Preview",
        mode: "BROWSE",
        error: '',
        backup : null,
        searchText : null,
        selectedCenter: {},
        selectedLocation: {},
        selectedManager: {},
        searchBox : {},
        
        newCenter: {},
        newLocation: {},
        managerToRegister: {},
        freeManagers: null,
        selected: '',
        selectedText: '',
         options: [{
          text: 'name',
          value: 'name'
        }, {
          text: 'type',
          value: 'type'
        }, {
          text: 'location',
          value: 'location'
        }, {
          text: 'average',
          value: 'average'
        }



       ]
         
    },
    mounted() {
        axios
            .get('rest/sportsCenters/getAll')
            .then(response => (this.sports_centers = response.data))
            
        
								
	 	this.newCenter = {
			id: '', name: null, centerType: null, manager: null, content: null,  status: false, location: null,
			imagePath: null, averageGrade: null, workingHours: null}
		this.newLocation = {id: '', street: null, city: null, postalCode: null, longitudeLength: null, latitudeWidth: null}
		this.managerToRegister = {id: '', userName: null, password: null, name: null, surname: null, sex: null, birthDate: null, role: 2}
		
    },
    methods: {
        search : function(tekst, val){
            this.mode = "SEARCH"
            if(!this.searchText){
                axios
                .get('rest/sportsCenters/getAll')
                .then(response => (this.sports_centers = response.data))
            }
            else{
                axios
                .get('rest/sportsCenters/search/' + this.searchText + ',' + this.selected)
                .then(response => (this.sports_centers = response.data))
            }
        },

        modeBrowse : function(){
            this.mode = "BROWSE"
            axios
            .get('rest/sportsCenters/getAll')
            .then(response => (this.sports_centers = response.data))
        },
        showAdd : function() {
			this.mode = "EDIT"
		},
        
        addCenter: function (event) {
			this.error = ""
			this.selectedCenter.id = 1;
			
			this.selectedCenter.location = this.selectedLocation
				axios.post('rest/sports_centers/addCenter', this.selectedCenter)
					.then((response) => {
						this.sports_centers = response.data
						if(sports_centers){
							alert("User successfuly registered")
						}else{
							alert("User already exists")
						}
						window.open("login.html", "_self");
					})

			

			event.preventDefault();
		}

    }

});

function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}