import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchBook'
})
export class SearchBookPipe implements PipeTransform {

  transform(books: any[], searchText: any, fieldName: string): unknown {

    if (!books) {
      return [];
    }

    if (!searchText) {
      return books;
    }

    console.log((typeof (searchText)));
    if (typeof (searchText) == 'string') {
      searchText = searchText.toLowerCase();
    }
    return books.filter(book => {
      if (book && book[fieldName]) {
        
        if(isNaN(searchText)) {
          return book[fieldName].toLowerCase().includes(searchText);
        } else {
          return book[fieldName].toString().includes(searchText);
        }
      }
    });
  }


}
