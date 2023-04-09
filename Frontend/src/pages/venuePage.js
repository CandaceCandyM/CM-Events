import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import VenueClient from "../api/venueClient";

class VenuePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['createVenue', 'renderVenue', 'renderVenues'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('new-venue-form').addEventListener('submit', this.createVenue);
        this.client = new VenueClient();

        this.renderVenues();
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderVenue() {
        let resultArea = document.getElementById("result-info");

        const event = this.dataStore.get("event");

        if (event) {
            resultArea.innerHTML = `
                <div>ID: ${event.id}</div>
                <div>Name: ${event.name}</div>
            `
        } else {
            resultArea.innerHTML = "No Such Event";
        }
    }

    async renderVenues() {
        let resultArea = document.getElementById("venue-list");

        const venues = await this.client.getAllVenues(this.errorHandler);
        resultArea.innerHTML = "";
        if (venues) {
            for(const venue of venues){
                resultArea.innerHTML += `
                    <div class="venue-results">
                    <h2><p class="name-result">${venue.name}</p></h2>
                    <p class="venue-id-result">${venue.id}</p>
                    <p class="desc-result">${venue.description}</p>
                    <p class="address-result">${venue.address}</p>
                    <p class="status-result">${venue.status}</p>
                    <p class="event-capacity-result">${venue.event_capacity}</p>
                    `
                if (venue.phone) {
                    resultArea.innerHTML += `
                        <p class="phone-result">${venue.phone}</p>
                        `
                }
                if (venue.website) {
                    resultArea.innerHTML += `
                        <p class="website-result"><a href="${venue.website}">${venue.website}</a></p>
                        `
                }
                if (venue.email) {
                    resultArea.innerHTML += `
                        <p class="email-result">${venue.email}</p>
                        `
                }
                resultArea.innerHTML += `</div>`
            }
        } else {
            resultArea.innerHTML = "No Venues";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async createVenue(event) {
        event.preventDefault();
        this.dataStore.set("venue", null);

        let venue_name = document.getElementById("venue-name-field").value;
        let description = document.getElementById("description-field").value;
        let address = document.getElementById("address-field").value;
        let status = document.getElementById("status-field").value;
        let event_capacity = document.getElementById("event-capacity-field").value;
        let phone = document.getElementById("phone-field").value;
        let website = document.getElementById("website-field").value;
        let email = document.getElementById("email-field").value;

        const createdVenue = await this.client.createVenue(venue_name, description, address, status, event_capacity,
            phone, website, email, this.errorHandler);
        this.dataStore.set("venue", createdVenue);

        if (createdVenue) {
            this.showMessage(`${createdVenue.venue_name} has been Created!`);
        } else {
            this.errorHandler("Error Creating Venue! Try again...");
        }
        this.renderVenues();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const venuePage = new VenuePage();
    venuePage.mount();
};

window.addEventListener('DOMContentLoaded', main);