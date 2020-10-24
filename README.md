### Functional Requirement (MVP)
- User should be able to register
- User should be able to CRUD their contents (externalUniqueId, title, tags, description, popularity, visibility)
- User should be able to search with specific keyword and get relevant contents
- Search result should be relevant to title, tags, description and filtered by visibility

### Technical Requirement (MVP)
- Search should be minimun latency
- Search should be accurate

### Design Consideration
- Will use full text search engine such as ElasticSearch to provide search with minimum latency and good accuracy
- Read heavy system

## API Design
- registerUser(name, email, password) : User
- auth(email, password) : Session
- addContent(externalUniqueId, title, description, popularity, visibility, tags, session): Content
- removeContent(contentId) : Void
- updateContent(title, description, popularity, visibility, tags, session): Content
- search(keyword): List<SearchResult>

### Database Design
- user
  - id (PK)
  - name (VARCHAR 64)
  - email (VARCHAR 64)
  - password (VARCHAR 64)

- content
  - id (PK)
  - externalUniqueId (VARCHAR 48)
  - title (VARCHAR 255)
  - description (TEXT)
  - popularityInPercentage (INT 2) // in percentage
  - visibility (INT 1) // 0 or 1
  - user_id (FK)
  - tags (TEXT) // in json array format