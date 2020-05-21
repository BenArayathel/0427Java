import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'magicpipe'
})
export class MagicpipePipe implements PipeTransform {

  transform(param1:string, param2:string) {
    // We can have super complex logic here, but this is just a simple example
    return param2.concat(param1);
  }

}
