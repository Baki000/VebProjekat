var app = new Vue({
	el: '#sportskiObjekti',
	data: {
		sportsCenters: [],
		tmp: [],
		searchIme: "",
		searchTip: "",
		searchLokacija: "",
		searchOcena: "",
		loggedUser: {},
		logovan: false,
		error: "",
		intId: "",
		isOpen: "closed",
		filterStatus: "",
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
		
		
		
		axios.get('rest/users/currentUser').then((response) => {
			this.loggedUser = response.data
			this.logovan = true
			})
			
			
		axios.get('rest/sportsCenters/getAll')
			.then((response) => {
				this.tmp = response.data; 
				for(let s of this.tmp){
					if(s.status === 'Opened'){
						this.sportsCenters.push(s);
					}
				}
				for(let s of this.tmp){
					if(s.status === 'Closed'){
						this.sportsCenters.push(s);
					}
				}
			})
		
	},
	methods: {
		search : function(tekst, val){
            if(!this.searchText){
                axios
                .get('rest/sportsCenters/getAll')
                .then(response => (this.sportsCenters = response.data))
            }
            else{
                axios
                .get('rest/sportsCenters/search/' + this.searchText + ',' + this.selected)
                .then(response => (this.sportsCenters = response.data))
            }
        },
		
		Selected: function(sp) {
			axios.post('rest/sportsCenters/setSelected', { id: sp.id } ).then((response)=>{
				window.location.href = 'http://localhost:8080/WebShopREST/oneCenter.html';
			})
			
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
		},
		
		logOut: function() {
			axios.get('rest/users/logout').then((response)=>{
				alert('Odjavili ste se!')
				window.open("main.html", "_self");
			}).catch(() =>{
					alert('Korisnik je vec odjavljen!')
					event.preventDefault();
					return;
				})
			
		}


	}
});