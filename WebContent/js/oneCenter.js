var app = new Vue({
	el: '#viewSO',
	data: {
		sc: {},
		error: '',
		trainings:[]
	},
	mounted() {
		axios.get('rest/sportsCenters/getSelected')
		.then((response) => {this.sc = response.data;
			axios.get('rest/training/getAll', { params: { idSportskogObjekta: this.sc.intId } }).
			then((response) => {
				this.trainings = response.data;
			})
			})
		},
	methods: {
		prijavaNaTrening: function(t){
			axios.put('rest/korisnik1/cekirajSe')
			.then((response) => {
				axios.post('rest/istorijaTreninga/cekirajSe', { intId: t.intId })
				.then((response) => {
					alert('Uspesno ste se cekirali za trening!')
				})
			}).catch(() => {
				alert('Istekla Vam je clanarina ili ste potrosili sve termine!')
				return;
			})
		},
		zakaziPersonalni: function(){
			window.location.href = 'http://localhost:8080/WebShopREST/zakazivanjePersonalnogTreninga.html';
		} 
	}
});