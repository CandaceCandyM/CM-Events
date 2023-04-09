import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import EventClient from "../api/eventClient";

class EventPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onCreate', 'renderEvent', 'renderEvents'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new EventClient();

        this.renderEvents();
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderEvent() {
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

    async renderEvents() {
        let resultArea = document.getElementById("public-events");

        const events = await this.client.getEvents(this.errorHandler);
        resultArea.innerHTML = "";
        if (events) {
            for(const event of events){
                resultArea.innerHTML += `
                    <div class="results">
                    <h3 class="name-result"><a href="rsvp.html?id=${event.id}">${event.event_name}</a></h3>
                    <p class="host-result">Host: ${event.username}</p>
                    <p class="category-result">${event.category}</p>
                    <p class="dates-result">${event.start_date} to ${event.end_date}</p>
                    <p class="desc-result">${event.description}</p>
                    <p class="venue-result">Venue: ${event.venue_id}</p>
                    </div>
                    `
            }
        } else {
            resultArea.innerHTML = "No Events";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("event", null);

        let result = await this.client.getEvent(id, this.errorHandler);
        this.dataStore.set("event", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        event.preventDefault();
        this.dataStore.set("event", null);

        let venue_id = document.getElementById("create-venue-id-field").value;
        let event_name = document.getElementById("create-event-name-field").value;
        let category = document.getElementById("create-category-field").value;
        let description = document.getElementById("create-description-field").value;
        let start_date = document.getElementById("create-start-date-field").value;
        let end_date = document.getElementById("create-end-date-field").value;
        let username = document.getElementById("create-username-field").value;

        const createdEvent = await this.client.createEvent(event_name, username, venue_id, description,
            start_date, end_date, category, this.errorHandler);
        this.dataStore.set("event", createdEvent);

        if (createdEvent) {
            this.showMessage(`Created ${createdEvent.event_name}!`);
        } else {
            this.errorHandler("Error creating! Try again...");
        }
        this.renderEvents();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const eventPage = new EventPage();
    eventPage.mount();
};

window.addEventListener('DOMContentLoaded', main);