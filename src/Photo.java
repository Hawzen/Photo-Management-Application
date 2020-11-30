public class Photo {
	String path;
	LinkedList<String> tags;
	// Constructor
	public Photo(String path, LinkedList<String> tags){
		this.path = path;
		this.tags = tags;
		// FIXME: Remove tag insert
		this.tags.insert("Everything");
	}

	// Return the path (full file name) of the photo. A photo is uniquely identified by its path.
	public String getPath() {
		return this.path;
	}

	// Return all tags associated with the photo
	public LinkedList<String> getTags() {
		return this.tags;
	}
}
