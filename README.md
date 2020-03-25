Sonarqube: http://EPBYMINW8126:9000/dashboard?id=com.epam.lab%3Anews

Jenkins ( user :Develop / password : Develop1234 ): http://EPBYMINW8126:8090/job/Git%20job/

Deploy link: http://EPBYMINW8126:8080/news

**Author**
*	***GET:*** */authors - Gets all authors;
*	***GET:*** */authors/{id} - Gets author by id;
*	***POST:*** */authors - Save author:

    Body in JSON-format: 
    {
        "name": "name",
        "surname": "surname"
    }

*	***PUT:*** */authors/{id} - Update author by id:

	Body in JSON-format:
    {
        "name": "name",
        "surname": "surname"
    }

*	***PATCH:*** */authors/{id} - Part update author by id:

	Body in JSON-format:
	{
        "name": "name",
        "surname": "surname"
    }
	
	or:
	
    {
        "name": "name"
    }
	
	or:
	
    {
        "surname": "surname"
    }
	
*	***DELETE:*** */authors/{id} - Delete author by id.

**Tag**
*	***GET:*** */tags - Gets all tags;
*	***GET:*** */tags/{id} - Gets tag by id;
*	***POST:*** */tags - Save tag:

    Body in JSON-format: 
    {
        "name": "name"
    }

*	***PUT:*** */tags/{id} - Update tag by id:

	Body in JSON-format:
    {
        "name": "name"
    }

*	***PATCH:*** - like ***PUT***
	
*	***DELETE:*** */tags/{id} - Delete tag by id.

**News**
*	***GET:*** */news - Gets all news;
*	***GET:*** */news/{id} - Gets news by id;
*	***POST:*** */news - Save news:

	Body in JSON-format:
    {
        "tittle": "title",
        "shortText": "shortText",
        "fullText": "fullText",
        "author": 
            {
                "name": "authorName",
                "surname": "authorSurname"
            },
        "tags": [
        	        {
        	        	"name": "tagName"
        	        },
        	        {
        		        "name": "tagName"
        	        }
                ]
    }

*	***PUT:*** */news/{id} - Update news by id:

	Body in JSON-format:
    {
        "tittle": "title",
        "shortText": "shortText",
        "fullText": "fullText",
        "author": 
            {
                "name": "authorName",
                "surname": "authorSurname"
            },
        "tags": [
        	        {
        	        	"name": "tagName"
        	        },
        	        {
        		        "name": "tagName"
        	        }
                ]
    }

*	***PATCH:*** */news/{id} - Part update news by id:

		Body in JSON-format:
    {
        "tittle": "title",
        "shortText": "shortText",
        "fullText": "fullText",
        "author": 
            {
                "name": "authorName",
                "surname": "authorSurname"
            },
        "tags": [
        	        {
        	        	"name": "tagName"
        	        },
        	        {
        		        "name": "tagName"
        	        }
                ]
    }

	or:
	
    {
        "tittle": "title",
        "shortText": "shortText",
        "fullText": "fullText"
    }

	or:
	
    {
        "author": 
            {
                "name": "authorName",
                "surname": "authorSurname"
            },
        "tags": [
        	        {
        	        	"name": "tagName"
        	        },
        	        {
        		        "name": "tagName"
        	        }
                ]
    }
	
	or:
	
    {
    }
	
	or another combination of news fields;

*	***DELETE:*** */news/{id} - Delete news by id.
*	***GET:*** */news/creationDate - Gets news sorted by creationDate.
*	***GET:*** */news/modificationDate - Gets news sorted by modificationDate.
*	***GET:*** */news/author - Gets news sorted by author(author name and then author surname).
*	***GET:*** */news/tags - Gets news sorted by tags count.
*	***GET:*** */news/count - Gets news number;
*	***POST:*** */news/search - Search news by author id and tags id:

	Body in JSON-format:
    {
        "author": 
            {
                "id": 10
            },
        "tags": [
        	        {
        	        	"id": 111
        	        },
        	        {
        		        "id": 101
        	        }
                ]
    }