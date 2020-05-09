export class NgxDatatableSort {
  dir: string;
  prop: string;

  constructor(rawObject: INgxDatatableSort) {
    if (rawObject) {
      this.dir = rawObject.dir;
      this.prop = rawObject.prop;
    }
  }
}

export interface INgxDatatableSort {
  dir: string;
  prop: string;
}
