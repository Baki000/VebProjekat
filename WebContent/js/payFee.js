var app = new Vue({
	el: '#payFee',
	data: {
		fees: [],
		loggedUser: {}
	},
	mounted() {
		axios.get('rest/users/currentUser')
			.then((response) => {
				this.loggedUser = response.data;
                fee1 = {id: 1, feeType:"daily", payDay: null, dateStart: null, dateEnd: null, price:500, customer: null, status: 'active', entries:1}
                fee2 = {id: 2, feeType:"monthly", payDay: null, dateStart: null, dateEnd: null, price:2500, customer: null, status: 'active', entries:30}
                fee3 = {id: 3, feeType:"yearly", payDay: null, dateStart: null, dateEnd: null, price:12000, customer: null, status: 'active', entries:365}
                this.fees.push(fee1);
                this.fees.push(fee2);
                this.fees.push(fee3);
            })
	},
	methods: {
		chooseFee: function(c) {
			axios.post('rest/fees/setSelected', c).then(response => {
				 window.open("payFee2.html", "_self");
			 })
		}


	}
});