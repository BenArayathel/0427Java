import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'prepend'
})
export class PrependPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }
}
