import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'altertext'
})
export class AlterTextPipe implements PipeTransform {

    // change this to my own function?
    transform(param1: string): any {
    return "All foods eaten.";
    
    }
    // override basically, maybe use one param and change text somehow
}
