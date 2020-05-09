export interface IResponseDTO<T, K = { [key: string]: any }> {
  result: T;
  error: string;
  success: string;
}
