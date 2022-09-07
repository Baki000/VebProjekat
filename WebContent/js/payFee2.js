var app = new Vue({
	el: '#fee',
	data: {
		fee: {},
		error: '',
		min: Date.now()
	},
	mounted() {
		axios.get('rest/fees/getSelected')
			.then((response) => {
				this.fee = response.data;
			})
			this.min = new Date().toISOString().split("T")[0];
	},
	methods: {
		payFee: function() {
			axios.post('rest/fees/', this.fee)
			.then((response) => {
				this.fee = response.data;
			})
			event.preventDefault();
		}
	}
});