var app = new Vue({
	el: '#sports_centers',
	data: {
		sports_centers: null,
		title: "Primer Vue.js tehnologije na spisku proizvoda",
		mode: "BROWSE",
		error: '',
		backup : null
	},
	mounted() {
		axios
			.get('rest/sportsCenters/getAll')
			.then(response => (this.sports_centers = response.data))
	},
	methods: {
		
	}
	
});
