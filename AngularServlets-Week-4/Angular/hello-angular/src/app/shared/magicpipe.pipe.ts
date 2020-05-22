import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'magicpipe'
})
export class MagicpipePipe implements PipeTransform {

  transform(param1: number, param2: string) {
    //We can have super complex logic here, but I don't have any imagination.
    return (param1.toString()).concat(param2);
  }

}
