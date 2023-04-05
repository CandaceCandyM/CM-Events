import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import EventClient from "../api/eventClient";

class EventPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'renderEvent', 'renderEvents'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        //document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
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
                    <h4><a href="rsvp.html?${event.id}">${event.event_name}</a></h4>
                    <p>${event.category}</p>
                    <p>${event.description}</p>
                    <p>${event.start_date} to ${event.end_date}</p>
                    <p>Host: ${event.username}</p>
                    <p>Venue: ${event.venue_id}</p>
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
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const eventPage = new EventPage();
    eventPage.mount();
};

window.addEventListener('DOMContentLoaded', main);