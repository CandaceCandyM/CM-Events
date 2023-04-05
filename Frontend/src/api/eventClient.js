import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
export default class EventClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getEventById', 'getEvents', 'createEvent'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Gets the concert for the given ID.
     * @param id Unique identifier for a concert
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The concert
     */
    async getEventById(id, errorCallback) {
        try {
            const response = await this.client.get(`/events/${id}`);
            return response.data;
        } catch (error) {
            this.handleError("getEventById", error, errorCallback)
        }
    }

    async getEvents(errorCallback) {
        try {
            const response = await this.client.get(`/events`);
            return response.data;
        } catch (error) {
            this.handleError("getEvents", error, errorCallback)
        }
    }

    async createEvent(event_name, username, venue_id, description, start_date, end_date, category, errorCallback) {
        try {
            const response = await this.client.post(`events`, {
                event_name: event_name,
                username: username,
                venue_id: venue_id,
                description: description,
                start_date: start_date,
                end_date: end_date,
                category: category
            });
            return response.data;
        } catch (error) {
            this.handleError("createEvent", error, errorCallback);
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}
