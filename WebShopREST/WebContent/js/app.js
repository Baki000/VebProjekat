var app = new Vue({
	el: '#sports_centers',
	data: {
		sports_centers: null,
		title: "Primer Vue.js tehnologije na spisku proizvoda",
		mode: "BROWSE",
		error: '',
		backup : null,
		searchText : null
	},
	mounted() {
		axios
			.get('rest/sportsCenters/getAll')
			.then(response => (this.sports_centers = response.data))
	},
	methods: {
		search : function(tekst){
			this.mode = "SEARCH"
			if(!this.searchText){
				axios
				.get('rest/sportsCenters/getAll')
				.then(response => (this.sports_centers = response.data))
			}
			else{
				axios
				.get('rest/sportsCenters/search/' + this.searchText)
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
