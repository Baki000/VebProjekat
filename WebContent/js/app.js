var app = new Vue({
	el: '#sports_centers',
	data: {
		sports_centers: null,
<<<<<<< HEAD
		title: "Primer Vue.js tehnologije na spisku proizvoda",
		mode: "BROWSE",
		error: '',
		backup : null
=======
		title: "Sports Centers Preview",
		mode: "BROWSE",
		error: '',
		backup : null,
		searchText : null,
		searchBox : {},
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
>>>>>>> d15706ed4e385a8183ed073cee91171707281a9b
	},
	mounted() {
		axios
			.get('rest/sportsCenters/getAll')
			.then(response => (this.sports_centers = response.data))
	},
	methods: {
<<<<<<< HEAD
=======
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
		}
>>>>>>> d15706ed4e385a8183ed073cee91171707281a9b
		
	}
	
});
