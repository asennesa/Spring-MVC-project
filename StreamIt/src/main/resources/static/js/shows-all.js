const URLS = {
    shows: '/api/shows',
}

fetch(URLS.shows)
    .then(response => response.json())
    .then(shows => {
        if(shows.length===0){
            let listItem = document.createElement('li');
            listItem.innerHTML="No events registered."
            document.getElementById('shows-list')
                .appendChild(listItem);
        }
        shows.forEach(show => {
            let myJSON = JSON.stringify(show);
            let aShow = JSON.parse(myJSON);
            let listItem = document.createElement('li');
            let hr = document.createElement('hr');
            listItem.innerHTML="Event name: "+aShow.showName
                +" Date: "+aShow.date.substring(0,19).replace("T"," ")
                +" Address: "+aShow.venueAddress
            document.getElementById('shows-list')
                .appendChild(listItem);
            document.getElementById('shows-list')
                .appendChild(hr);
        });
    });