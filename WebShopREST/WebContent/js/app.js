var app = new Vue({
	el: '#sports_centers',
	data: {
		sports_centers: null,
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
	},
	mounted() {
		axios
			.get('rest/sportsCenters/getAll')
			.then(response => (this.sports_centers = response.data))
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
		}
		
	}
	
});
