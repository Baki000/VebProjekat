var app = new Vue({
	el: '#users',
	data: {
		users: null,
		searchName: "",
		searchSurname: "",
		searchUsername: "",
		loggedUser: {},
		tipKorisnika: "",
		options: [{
          text: 'name',
          value: 'name'
        }, {
          text: 'surname',
          value: 'surname'
        }, {
          text: 'username',
          value: 'username'
        }]
	},
	mounted() {
		axios.get('rest/users/getAll')
			.then(response => (this.users = response.data))
		axios.get('rest/users/currentUser').then((response) => {
			this.loggedUser = response.data
			})
	},
	methods: {
		search : function(tekst, val){
            if(!this.searchText){
                axios
                .get('rest/users/getAll')
                .then(response => (this.users = response.data))
            }
            else{
                axios
                .get('rest/users/search/' + this.searchText + ',' + this.selected)
                .then(response => (this.users = response.data))
            }
        },
		Sort: function(n) {
			//function found on site: https://www.w3schools.com/howto/howto_js_sort_table.asp
			  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
			  table = document.getElementById("tabela");
			  switching = true;
			  dir = "asc";
			  while (switching) {
			    switching = false;
			    rows = table.rows;
			    for (i = 1; i < (rows.length - 1); i++) {
			      shouldSwitch = false;
			      x = rows[i].getElementsByTagName("TD")[n];
			      y = rows[i + 1].getElementsByTagName("TD")[n];
			      if (dir == "asc") {
			        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
			          shouldSwitch = true;
			          break;
			        }
			      } else if (dir == "desc") {
			        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
			          shouldSwitch = true;
			          break;
			        }
			      }
			    }
			    if (shouldSwitch) {
			      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			      switching = true;
			      switchcount ++;
			    } else {
			      if (switchcount == 0 && dir == "asc") {
			        dir = "desc";
			        switching = true;
			      }
			    }
			  }
		}


	}
});